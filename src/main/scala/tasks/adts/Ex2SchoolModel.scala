package tasks.adts
import u03.Sequences.*
import u03.Sequences.Sequence.Cons
import u03.Sequences.Sequence.Nil
import u03.Optionals.*
import u02.AlgebraicDataTypes.Person

/*  Exercise 2: 
 *  Implement the below trait, and write a meaningful test.
 *  Suggestion: 
 *  - reuse Sequences and Optionals as imported above
 *  - Course is a simple case classes with just the name
 *  - Teacher is a case class with name and sequence of courses
 *  - School is a case class with (sequences of) teachers and courses
 *  - add/set methods below create the new school 
 */

object SchoolModel:

  trait SchoolModel:
    type School
    type Teacher
    type Course
    extension (school: School)
      def addTeacher(name: String): School
      def addCourse(name: String): School
      def teacherByName(name: String): Optional[Teacher]
      def courseByName(name: String): Optional[Course]
      def nameOfTeacher(teacher: Teacher): String
      def nameOfCourse(teacher: Course): String
      def setTeacherToCourse(teacher: Teacher, course: Course): School
      def coursesOfATeacher(teacher: Teacher): Sequence[Course]


  object SchoolModelImpl extends SchoolModel:
    case class Course(name: String)
    case class Teacher(name: String, courses: Sequence[Course])
    case class School(courses: Sequence[Course], teachers: Sequence[Teacher])
    extension (school: School)
      def addTeacher(name: String): School = school match
        case School(courses, teachers) => School(courses, addToSequence(teachers, Teacher(name, Nil())))      

      def addCourse(name: String): School = school match
          case School(courses, teachers) => School(addToSequence(courses, Course(name)), teachers) 
        
      def teacherByName(name: String): Optional[Teacher] = school match
        case School(_, teachers) => Sequence.filter(teachers)(_ match
          case Teacher(currentName, _) => currentName == name
        ) match
          case Cons(Teacher(name, courses), _) => Optional.Just(Teacher(name, courses))
          case _ => Optional.Empty()
      
      def courseByName(name: String): Optional[Course] = school match
        case School(courses, _) => Sequence.filter(courses)(_ match
          case Course(currentName) => currentName == name
        ) match
          case Cons(Course(name), _) => Optional.Just(Course(name))
          case _ => Optional.Empty()
        
      
      def nameOfTeacher(teacher: Teacher): String = teacher match
        case Teacher(name, _) => name
      
      def nameOfCourse(teacher: Teacher): String = ???
      def setTeacherToCourse(teacher: Teacher, course: Course): School = ???
      
      def coursesOfATeacher(teacher: Teacher): Sequence[Course] = teacher match
        case Teacher(_, courses) => courses
      

    private def addToSequence[E](s: Sequence[E], el: E): Sequence[E] = s match
      case Cons(head, tail) => Cons(head, addToSequence(tail, el))
      case Nil() => Cons(el, Nil())

