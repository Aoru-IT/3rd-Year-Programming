using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace AlonzoEA1_316LA___Grade_Calculator
{
    public partial class Form1 : Form
    {
        private Calculator calculator;
        public Form1()
        {
            InitializeComponent();
            calculator = new Calculator();
        }

        private void btnCompute_Click(object sender, EventArgs e)
        {
            performOperation(calculator.GradeCalculation);
        }

        private void performOperation(Operation operation)
        {
            double num1 = double.Parse(txtMidterm.Text);
            double num2 = double.Parse(txtFinals.Text);

            lblAverage.Text = $"Average Grade: {operation(num1, num2)}";
        }

    }
}
