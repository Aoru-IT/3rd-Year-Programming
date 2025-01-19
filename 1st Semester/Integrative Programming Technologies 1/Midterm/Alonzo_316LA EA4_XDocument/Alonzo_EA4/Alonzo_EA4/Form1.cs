using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Xml.Linq;

namespace Alonzo_EA4
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string id = textBoxID.Text;
            string lastName = textBoxLastName.Text;
            string firstName = textBoxFirstName.Text;
            string course = textBoxCourse.Text;
            int units;
            
            if (!int.TryParse(textBoxUnits.Text, out units))
            {
                MessageBox.Show("Please enter a valid Academic Units");
                return;
            }

            if (string.IsNullOrEmpty(id) || string.IsNullOrEmpty(lastName) || string.IsNullOrEmpty(firstName) || string.IsNullOrEmpty(course))
            {
                MessageBox.Show("Please enter all fields.");
                return;
            }

            double partialFee = PartialFee(units);
            double discount = DiscountComputation(partialFee, units);
            double totalFee = TotalFeeComputation(partialFee, discount);

            XElement student = new XElement("Student",
               new XElement("ID", id),
               new XElement("LastName", lastName),
               new XElement("FirstName", firstName),
               new XElement("Course", course),
               new XElement("Units", units),
               new XElement("PartialFee", partialFee),
               new XElement("Discount", discount),
               new XElement("TotalFee", totalFee)
           );

            XDocument doc;
            string xmlFile = "Students.xml";

            if (System.IO.File.Exists(xmlFile))
            {
                doc = XDocument.Load(xmlFile); // Load the existing file
                doc.Element("Student").Add(student); // Add new employee to the root
            }
            else
            {
                // Create a new XML document if file doesn't exist
                doc = new XDocument(
                    new XElement("Student", student));
            }

            doc.Save(xmlFile);
            MessageBox.Show("Student data saved to XML successfully.");
        }

        private double PartialFee(int academicUnits)
        {
            return academicUnits * 500;
        }

        private double DiscountComputation(double partialFee, int academicUnits)
        {
            if (academicUnits >= 15 && academicUnits < 20)
            {
                return partialFee * 0.03;
            }
            else if (academicUnits >= 20 && academicUnits < 24)
            {
                return partialFee * 0.05;
            }
            else if (academicUnits >= 24)
            {
                return partialFee * 0.07;
            }
            else
            {
                return partialFee * 0;
            }
        }

        private double TotalFeeComputation(double partialFee, double discount)
        {
            return partialFee - discount;
        }
        
        private void button2_Click(object sender, EventArgs e)
        {
            listBox1.Items.Clear();  // Clear existing items in the list
            string xmlFile = "Students.xml";

            if (System.IO.File.Exists(xmlFile))
            {
                XDocument doc = XDocument.Load(xmlFile);
                XElement studentElements = doc.Element("Student");

                foreach (XElement student in studentElements.Elements("Student"))
                {
                    string id = student.Element("ID")?.Value ?? "No ID";
                    string lastName = student.Element("LastName")?.Value ?? "No Last Name";
                    string firstName = student.Element("FirstName")?.Value ?? "No First Name";
                    string course = student.Element("Course")?.Value ?? "No Course";
                    int units = int.Parse(student.Element("Units")?.Value ?? "0");
                    double partialFee = Double.Parse(student.Element("PartialFee")?.Value ?? "0");
                    double discount = Double.Parse(student.Element("Discount")?.Value ?? "0");
                    double totalFee = Double.Parse(student.Element("TotalFee")?.Value ?? "0");

                    listBox1.Items.Add($"ID:{id}");
                    listBox1.Items.Add($"Last Name: {lastName}");
                    listBox1.Items.Add($"FirstName: {firstName}");
                    listBox1.Items.Add($"Course: {course}");
                    listBox1.Items.Add($"Units: {units.ToString()}");
                    listBox1.Items.Add($"Partial Fee: {partialFee.ToString()}");
                    listBox1.Items.Add($"Discount: {discount.ToString()}");
                    listBox1.Items.Add($"Total Fee: {totalFee.ToString()}");
                    listBox1.Items.Add(new string('-', 40));
                }
            }
            else
            {
                MessageBox.Show("No XML file found. Please add data first.");
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            listBox1.Items.Clear();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            string xmlFile = "Students.xml";

            // Check if the file exists
            if (System.IO.File.Exists(xmlFile))
            {
                // Delete the file
                System.IO.File.Delete(xmlFile);
                MessageBox.Show("XML file cleared successfully.");

                // Optionally, clear the ListBox as well
                listBox1.Items.Clear();
            }
            else
            {
                MessageBox.Show("No XML file found.");
            }
        }

        private void button5_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }
    }
 }
