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
using System.Xml.XPath;

namespace Alonzo_Introduction
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            string name = txtName.Text;
            string birthday = txtBirthday.Text;
            string age = txtAge.Text;

            string hobby1 = txtHobby1.Text;
            string hobby2 = txtHobby2.Text;
            string interest1 = txtInterest1.Text;
            string interest2 = txtInterest2.Text;   

            if (string.IsNullOrEmpty(name) || string.IsNullOrEmpty(birthday) || string.IsNullOrEmpty(age)
                    || string.IsNullOrEmpty(hobby1) || string.IsNullOrEmpty(hobby2) ||  string.IsNullOrEmpty(interest1) || string.IsNullOrEmpty(interest2))                       
            {
                  MessageBox.Show("Please enter your information on all necessary fields.");
                  return;
            }

            XElement person = new XElement("Person",
                new XElement("Name", name),
                new XElement("Birthday", birthday),
                new XElement("Age", age),
                new XElement("Hobby1", hobby1),
                new XElement("Hobby2", hobby2),
                new XElement("Interest1", interest1),
                new XElement("Interest2", interest2)
            );

            XDocument doc;
            string xmlFile = "people.xml";

            if (System.IO.File.Exists(xmlFile))
            {
                doc = XDocument.Load(xmlFile);
                doc.Element("People").Add(person);
            }
            else
            {
                doc = new XDocument(
                    new XElement("People", person)
                );
            }

            doc.Save(xmlFile);
            MessageBox.Show("Data saved to XML successfully.");
        }

        private void btnLoad_Click(object sender, EventArgs e)
        {
            listInformation.Items.Clear();  
            string xmlFile = "people.xml";

            if (System.IO.File.Exists(xmlFile))
            {
                XDocument doc = XDocument.Load(xmlFile);
                foreach (XElement person in doc.Element("People").Elements("Person"))
                {
                    string name = person.Element("Name").Value;
                    string birthday = person.Element("Birthday").Value;
                    string age = person.Element("Age").Value;
                    string hobby1 = person.Element("Hobby1").Value;
                    string hobby2 = person.Element("Hobby2").Value;
                    string interest1 = person.Element("Interest1").Value;
                    string interest2 = person.Element("Interest2").Value;

                    listInformation.Items.Add($"Name: {name}, Birthday: {birthday}, Age: {age}");
                    listInformation.Items.Add($"First Hobby: {hobby1}, Second Hobby: {hobby2}");
                    listInformation.Items.Add($"First Interest: {interest1}, Second Interest: {interest2}");

                }
            }
            else
            {
                MessageBox.Show("No XML file found. Please save data first.");
            }
        }

        private void btnClear_Click(object sender, EventArgs e)
        {
            listInformation.Items.Clear(); 
        }


    }
}
