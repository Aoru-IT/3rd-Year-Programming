using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AlonzoEA1_316LA___Grade_Calculator
{
    public delegate double Operation(double x, double y);
    public class Calculator
    {
        public double GradeCalculation(double x, double y)
        {
            return (x + y)/2;
        }
    }
}
