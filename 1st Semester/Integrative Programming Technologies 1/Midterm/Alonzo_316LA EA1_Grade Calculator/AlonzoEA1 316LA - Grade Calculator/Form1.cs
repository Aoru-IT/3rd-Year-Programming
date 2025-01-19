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
        public Form1()
        {
            InitializeComponent();
        }

        private void btnCompute_Click(object sender, EventArgs e)
        {
            double midterm = Convert.ToDouble(txtMidterm.Text);
            double finals = Convert.ToDouble(txtFinals.Text);

            double average = (midterm + finals) / 2;
            lblAverage.Text = average.ToString();
        }

     
    }
}
