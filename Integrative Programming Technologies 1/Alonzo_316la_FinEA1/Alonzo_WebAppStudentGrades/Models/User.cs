namespace Alonzo_WebAppStudentGrades.Models
{
    public class User
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Section { get; set; }
        public string Address { get; set; }
        public int Age { get; set; }
        public double MidtermGrade {  get; set; }
        public double FinalGrade {  get; set; }

        public double SemesterGrade => (MidtermGrade + FinalGrade) / 2;
    }
}
