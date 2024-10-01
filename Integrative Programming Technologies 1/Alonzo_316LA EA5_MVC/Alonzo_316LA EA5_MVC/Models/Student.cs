using System.Reflection.Metadata;

namespace Alonzo_316LA_EA5_MVC.Models
{
    public class Student
    {
        public string LastName { get; set; }
        public string FirstName { get; set; }
        public string Section { get; set; }

        public decimal AverageGrade { get; set; }
        public decimal Remarks { get; set; }
        public decimal integProgGrade { get; set; }
        public decimal webDevGrade { get; set; }
        public decimal mobileEnterpGrade { get; set; }
        public decimal infoSecGrade { get; set; }
        public decimal network2Grade { get; set; }
        public decimal contempoGrade { get; set; }

        public decimal Average()
        {
            AverageGrade = (integProgGrade + webDevGrade + mobileEnterpGrade
                + infoSecGrade + network2Grade + contempoGrade)/6;

            Remarks = GetRemark(AverageGrade);
            return Math.Round(AverageGrade, 2);
        }

        public decimal GetRemark(decimal average)
        {
            if (average >= 98) return 4.00M;
            else if (average >= 95) return 3.75M;
            else if (average >= 92) return 3.50M;
            else if (average >= 89) return 3.25M;
            else if (average >= 86) return 3.00M;
            else if (average >= 83) return 2.75M;
            else if (average >= 80) return 2.50M;
            else if (average >= 77) return 2.25M;
            else if (average >= 74) return 2.00M;
            else if (average >= 71) return 1.75M;
            else if (average >= 68) return 1.50M;
            else if (average >= 64) return 1.25M;
            else if (average >= 60) return 1.00M;
            return 0M;
        }
    }
}

