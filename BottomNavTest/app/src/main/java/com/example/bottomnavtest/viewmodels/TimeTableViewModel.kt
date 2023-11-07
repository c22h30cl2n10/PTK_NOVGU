package com.example.bottomnavtest.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnavtest.domain.data.TimeTableSortType
import com.example.bottomnavtest.domain.data.database.AuditoriumsState
import com.example.bottomnavtest.domain.data.database.ComdoGroupsState
import com.example.bottomnavtest.domain.data.database.GroupsState
import com.example.bottomnavtest.domain.data.database.TeachersState
import com.example.bottomnavtest.domain.data.database.TimeTableEvents
import com.example.bottomnavtest.domain.data.database.TimeTableState
import com.example.bottomnavtest.domain.data.database.dao.AuditoriumsDao
import com.example.bottomnavtest.domain.data.database.dao.GroupsDao
import com.example.bottomnavtest.domain.data.database.dao.TeachersDao
import com.example.bottomnavtest.domain.data.database.dao.TimeTableDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TimeTableViewModel(
    private val timeTableDao: TimeTableDao,
    private val auditoriumDao: AuditoriumsDao,
    private val groupsDao: GroupsDao,
    private val teacherDao: TeachersDao
) : ViewModel() {

    private val _isFilterOpen = MutableStateFlow(false)
    val isFilterOpen = _isFilterOpen.asStateFlow()

    fun changeFilterState() {
        _isFilterOpen.value = !_isFilterOpen.value
    }

    val groupCode = MutableStateFlow("")
    val prepodCode = MutableStateFlow("")
    val classroomCode = MutableStateFlow("")
    val timeTableTitle = MutableStateFlow("")


    /*private var _newId = MutableStateFlow(0)
    private var _newWeek = MutableStateFlow(true)
    private var _newWeekDay = MutableStateFlow(0)
    private var _newSubGroup = MutableStateFlow('1')
    private var _newGroup = MutableStateFlow("")
    private var _newDiscipline = MutableStateFlow("")
    private var _newTeacher = MutableStateFlow("")
    private var _newAuditorium = MutableStateFlow("312")
    private var _newStartOfClasses = MutableStateFlow("")
    private var _newEndOfClasses = MutableStateFlow("")
    private var _newTimeTableNumber = MutableStateFlow(0)*/

    //Опредеяем цифру первго курса
    val currentDate = Date()
    val dateFormatYear = SimpleDateFormat("yyyy", Locale.getDefault())
    val dateFormatMonth = SimpleDateFormat("MM", Locale.getDefault())

    val year = dateFormatYear.format(currentDate).toInt()
    val month = dateFormatMonth.format(currentDate).toInt()
    val groupsPattern = MutableStateFlow(
        if (month >= 9) {
            year % 10
        } else {
            year % 10 - 1
        }
    )
    /*fun checkKoursesNumber() {
        if (month >= 9) {
            groupsPattern.value = year % 10
        }else{
            groupsPattern.value = year % 10 - 1
        }
    }*/

    fun ChangeGroup(number: String) {
        Log.d("Debug", "Changing groupCode to $number")
        groupCode.value = number
        prepodCode.value = ""
        classroomCode.value = ""
    }

    fun ChangePrepod(name: String) {
        prepodCode.value = name
        classroomCode.value = ""
        groupCode.value = ""
    }

    fun ChangeClassroom(classroom: String) {
        classroomCode.value = classroom
        groupCode.value = ""
        prepodCode.value = ""
    }

    //TimeTableState
    private val _sortType = MutableStateFlow(TimeTableSortType.CLASSROOM)
    private val _timetableList = _sortType
        .flatMapLatest { sortType ->
            when (sortType) {
                TimeTableSortType.GROUP -> {
                    Log.d("Debug", "Group changed ${groupCode.value}")
                    timeTableDao.getTimeTableForGroup(groupCode.value)
                }

                TimeTableSortType.PREPOD -> {
                    //Переписать на id, но позже
                    Log.d("Debug", "Prepod changed ${prepodCode.value}")
                    timeTableDao.getTimeTableForTeacher(prepodCode.value)
                }

                TimeTableSortType.CLASSROOM -> {
                    //Поменять под переменную, после создания списка кабинетов
                    Log.d("Debug", "Classroom changed ${classroomCode.value}")
                    timeTableDao.getTimeTableForAuditorium(classroomCode.value)
                }
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(TimeTableState())
    val timeTableState =
        combine(_state, _sortType, _timetableList) { state, sortType, timetableList ->
            state.copy(
                timeTableList = timetableList,
                sortType = sortType
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TimeTableState())

    //AuditoriumsState
    private val _auditoriumsState = MutableStateFlow(AuditoriumsState())
    private val _auditoriumsList = auditoriumDao.getAuditoriumsOrderByIdAsc()
    val auditoriumsState = combine(_auditoriumsState, _auditoriumsList) { state, auditoriumsList ->
        state.copy(
            auditoriumsList = auditoriumsList
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AuditoriumsState())

    //TeachersState
    private val _teachersState = MutableStateFlow(TeachersState())
    private val _teachersList = teacherDao.getTeachersOrderByIdAsc()
    val teachersState = combine(_teachersState, _teachersList) { state, teachersList ->
        state.copy(
            teachersList = teachersList
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TeachersState())

    //GroupsState
    private val _groupsState = MutableStateFlow(GroupsState())
    private val _comboGroupState = MutableStateFlow(ComdoGroupsState())

    private val _numberOfKours = MutableStateFlow(1)
    private val _groupsList = _numberOfKours
        .flatMapLatest { number ->
            when (number) {
                1 -> {
                    Log.d("Debug", "Number of kours ${groupsPattern.value}")
                    groupsDao.getGroupsByKoursSortByNumber(groupsPattern.value)
                }

                2 -> {
                    Log.d("Debug", "Number of kours ${groupsPattern.value - 1}")
                    groupsDao.getGroupsByKoursSortByNumber(groupsPattern.value - 1)
                }

                3 -> {
                    Log.d("Debug", "Number of kours ${groupsPattern.value - 2}")
                    groupsDao.getGroupsByKoursSortByNumber(groupsPattern.value - 2)
                }

                4 -> {
                    Log.d("Debug", "Number of kours ${groupsPattern.value - 3}")
                    groupsDao.getGroupsByKoursSortByNumber(groupsPattern.value - 3)
                }

                else -> {
                    Log.d("Debug", "Ошибка в выворе курса")
                    groupsDao.getGroupsOrderByIdAsc()
                }
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val groupsState =
        combine(_groupsState, _groupsList, _numberOfKours) { state, groupsList, numberOfKours ->
            state.copy(
                groupsList = groupsList,
                numberOfKours = numberOfKours
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), GroupsState())

    //Вариант фильтра на уровне view
    private val _allGroupsList = groupsDao.getGroupsOrderByIdAsc()
    val allGroupsState = combine(_groupsState, _allGroupsList) { state, groupsList ->
        state.copy(
            groupsList = groupsList,
            numberOfKours = groupsPattern.value
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), GroupsState())

    //Вариант - НАДЕЖНЫЙ
    private val _firstKoursGroupsList =
        groupsDao.getGroupsByKoursSortByNumber(groupsPattern.value)
    private val _secondKoursGroupsList =
        groupsDao.getGroupsByKoursSortByNumber(groupsPattern.value - 1)
    private val _thirdKoursGroupsList =
        groupsDao.getGroupsByKoursSortByNumber(groupsPattern.value - 2)
    private val _fourthKoursGroupsList =
        groupsDao.getGroupsByKoursSortByNumber(groupsPattern.value - 3)

    val comdoGroupsState =
        combine(
            _comboGroupState,
            _firstKoursGroupsList,
            _secondKoursGroupsList,
            _thirdKoursGroupsList,
            _fourthKoursGroupsList
        ) { state, firstKoursGroupsList, secondKoursGroupsList, thirdKoursGroupsList, fourthKoursGroupsList ->
            state.copy(
                first = firstKoursGroupsList,
                second = secondKoursGroupsList,
                third = thirdKoursGroupsList,
                fourth = fourthKoursGroupsList,
                numberOfKours = groupsPattern.value
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ComdoGroupsState())


    fun getCurrentDate() {
        //Группы
        /*val groupListTest = listOf(
            Groups(id = 1, number = "0902"),
            Groups(id = 2, number = "1902"),
            Groups(id = 3, number = "2902"),
            Groups(id = 4, number = "3902")
        )
        runBlocking {
            launch(Dispatchers.IO) {
                groupListTest.forEach { group ->
                    groupsDao.upsertGroups(group)
                }
            }
        }

        //Аудитории
        val auditoriums = listOf(
            Auditoriums(id = 1, number = "119"),
            Auditoriums(id = 2, number = "120"),
            Auditoriums(id = 3, number = "121"),
            Auditoriums(id = 4, number = "122"),
            Auditoriums(id = 5, number = "123"),
            Auditoriums(id = 6, number = "124"),
            Auditoriums(id = 7, number = "125"),
            Auditoriums(id = 8, number = "126"),
            Auditoriums(id = 9, number = "127"),
            Auditoriums(id = 10, number = "128"),
        )
        runBlocking {
            launch(Dispatchers.IO) {
                auditoriums.forEach { auditorium ->
                    auditoriumDao.upsertAuditoriums(auditorium)
                }
            }
        }

        //Преподаватели
        val teachers = listOf(
            Teachers(id = 1, firstName = "Артем", middleName = "Евгеньевич", lastName = "Дубогрей"),
            Teachers(id = 2, firstName = "Наталья", middleName = "Владимировна", lastName = "Сазонова"),
            Teachers(id = 3, firstName = "Наталья", middleName = "Владимировна", lastName = "Скачек"),
        )
        runBlocking {
            launch(Dispatchers.IO) {
                teachers.forEach { teacher ->
                    teacherDao.upsertTeachers(teacher)
                }
            }
        }

        //Расписание
        val timeTables = listOf(
            TimeTable(
                id = 1,
                week = true,
                weekDay = 1,
                subGroup = '0',
                group = "0902",
                discipline = "Разработка мобильных приложений",
                teacher = "Дубогрей",
                auditorium = "128",
                timeTableNumber = 1,
                startOfClasses = "8:30",
                endOfClasses = "10:10",
            ),
            TimeTable(
                id = 2,
                week = true,
                weekDay = 1,
                subGroup = '0',
                group = "0902",
                discipline = "Технология разработки программного обеспечения",
                teacher = "Сазонова",
                auditorium = "120",
                timeTableNumber = 2,
                startOfClasses = "10:20",
                endOfClasses = "12:00",
            ),
            TimeTable(
                id = 3,
                week = true,
                weekDay = 1,
                subGroup = '0',
                group = "0902",
                discipline = "Английский язык",
                teacher = "Кручинина",
                auditorium = "126",
                timeTableNumber = 3,
                startOfClasses = "12:45",
                endOfClasses = "14:25",
            ),
            TimeTable(
                id = 4,
                week = true,
                weekDay = 2,
                subGroup = '0',
                group = "1902",
                discipline = "Графический гизайн",
                teacher = "Дубогрей",
                auditorium = "128",
                timeTableNumber = 1,
                startOfClasses = "8:30",
                endOfClasses = "10:10",
            ),

            TimeTable(
                id = 5,
                week = true,
                weekDay = 2,
                subGroup = '0',
                group = "1902",
                discipline = "Английский язык",
                teacher = "Кручинина",
                auditorium = "126",
                timeTableNumber = 2,
                startOfClasses = "10:20",
                endOfClasses = "12:00",
            ),
        )
        runBlocking {
            launch(Dispatchers.IO) {
                timeTables.forEach { timeTable ->
                    timeTableDao.upsertTimeTable(timeTable)
                }
            }
        }*/

        Log.d("Debug", "Текущая дата год: ${year}")
        Log.d("Debug", "Текущая дата месяц: ${month}")
        Log.d("Debug", "Курс 1 = : ${groupsPattern.value}")
        Log.d("Debug", "Курс 2 = : ${groupsPattern.value - 1}")
        Log.d("Debug", "Курс 3 = : ${groupsPattern.value - 2}")
        Log.d("Debug", "Курс 4 = : ${groupsPattern.value - 3}")
    }


    fun onEvent(event: TimeTableEvents) {
        when (event) {
            is TimeTableEvents.DeleteTimeTable -> {
                viewModelScope.launch {
                    timeTableDao.deleteTimeTable(event.timeTable)
                }
            }

            TimeTableEvents.HideDialog -> TODO()
            /*TimeTableEvents.SaveTimeTable -> {
                val newId = _newId.value
                val newWeek = _newWeek.value
                val newWeekDay = _newWeekDay.value
                val newGroup = _newGroup.value
                val newSubGroup = _newSubGroup.value
                val nameOfPara = _newDiscipline.value
                val nameOfPrepod = _newTeacher.value
                val nameOfKabinet = _newAuditorium.value
                val startOfClasses = _newStartOfClasses.value
                val endOfClasses = _newEndOfClasses.value
                val timeTableNumber = _newTimeTableNumber.value

                if (nameOfPara.isBlank() || nameOfPrepod.isBlank() || nameOfKabinet.isBlank()) {
                    return
                }

                val timeTable = TimeTable(
                    discipline = nameOfPara,
                    auditorium = nameOfKabinet,
                    teacher = nameOfPrepod,
                    group = newGroup,
                    subGroup = newSubGroup,
                    week = newWeek,
                    weekDay = newWeekDay,
                    id = newId,
                    startOfClasses = startOfClasses,
                    endOfClasses = endOfClasses,
                    timeTableNumber = timeTableNumber,
                )
                viewModelScope.launch {
                    timeTableDao.upsertTimeTable(timeTable)
                }
            }*/

            TimeTableEvents.ShowDialog -> TODO()
            is TimeTableEvents.SortTimeTable -> {
                _sortType.value = event.timeTableSortType
            }
            else -> {}
        }
    }
}