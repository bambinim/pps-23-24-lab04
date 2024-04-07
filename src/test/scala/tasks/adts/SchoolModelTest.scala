package tasks.adts

import org.junit.*
import org.junit.Assert.*
import u03.Sequences.*
import u03.Sequences.Sequence.Cons
import u03.Sequences.Sequence.Nil
import u03.Optionals.Optional.Just
import u03.Optionals.Optional.Empty
import SchoolModel.*
import SchoolModel.SchoolModelImpl.School
import SchoolModel.SchoolModelImpl.Course
import SchoolModel.SchoolModelImpl.Teacher

class SchoolModelTest:

    @Test def testTeacherSearchByName(): Unit =
        val school: School = School(
            Nil(),
            Cons(Teacher("MV", Nil()), Cons(Teacher("AR", Nil()), Nil()))
        )
        assertEquals(Just(Teacher("MV", Nil())), school.teacherByName("MV"))
        assertEquals(Just(Teacher("AR", Nil())), school.teacherByName("AR"))
        assertEquals(Empty(), school.teacherByName("AA"))

    @Test def testCourseSearchByName(): Unit =
        val school: School = School(
            Cons(Course("PPS"), Cons(Course("PCD"), Nil())),
            Nil()
        )
        assertEquals(Just(Course("PPS")), school.courseByName("PPS"))
        assertEquals(Just(Course("PCD")), school.courseByName("PCD"))
        assertEquals(Empty(), school.courseByName("OOP"))

    @Test def testAddCourse(): Unit =
        val school: School = School(
            Cons(Course("PPS"), Cons(Course("PCD"), Nil())),
            Nil()
        )
        val updatedSchool: School = school.addCourse("OOP")
        assertEquals(Just(Course("OOP")), updatedSchool.courseByName("OOP"))

    @Test def testAddTeacher(): Unit =
        val school: School = School(
            Nil(),
            Cons(Teacher("MV", Nil()), Cons(Teacher("AR", Nil()), Nil()))
        )
        val updatedSchool: School = school.addTeacher("AA")
        assertEquals(Just(Teacher("AA", Nil())), updatedSchool.teacherByName("AA"))
