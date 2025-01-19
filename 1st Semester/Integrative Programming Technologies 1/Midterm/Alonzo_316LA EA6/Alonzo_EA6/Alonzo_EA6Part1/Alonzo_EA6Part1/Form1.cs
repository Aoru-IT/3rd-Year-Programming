using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Windows.Forms.VisualStyles;

namespace Alonzo_EA6Part1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void btnCompute_Click(object sender, EventArgs e)
        {
            int friesQty = (txtFries.Text == String.Empty) ? 0 : Convert.ToInt32(txtFries.Text);
            int burgerQty = (txtBurger.Text == String.Empty) ? 0 : Convert.ToInt32(txtBurger.Text);
            int spaghettiQty = (txtSpaghetti.Text == String.Empty) ? 0 : Convert.ToInt32(txtSpaghetti.Text);
            int drinksQty = (txtDrinks.Text == String.Empty) ? 0 : Convert.ToInt32(txtDrinks.Text);
            int chickenQty = (txtChicken.Text == String.Empty) ? 0 : Convert.ToInt32(txtChicken.Text);

            decimal friesPrice = 72.00M;
            decimal burgerPrice = 58.00M;
            decimal spaghettiPrice = 65.00M;
            decimal drinksPrice = 39.00M;
            decimal chickenPrice = 98.00M;

            decimal subtotal = qtyPrice(friesQty, friesPrice) + qtyPrice(burgerQty, burgerPrice) +
                qtyPrice(spaghettiQty, spaghettiPrice) + qtyPrice(drinksQty, drinksPrice) +
                qtyPrice(chickenQty, chickenPrice);

            decimal discount = discountCalc(subtotal);
            decimal total = subtotal - discount;

            displayQuantities(friesQty, burgerQty, spaghettiQty, drinksQty, chickenQty);
            lblSubtotal.Text = $"Subtotal: {subtotal.ToString("F2")}";
            lblDiscount.Text = $"Discount: {discount.ToString("F2")}";
            lblTotal.Text = $"Total: {total.ToString("F2")}";
        }

        public decimal qtyPrice(decimal qty, decimal price)
        {
            return qty * price;
        }

        public void displayQuantities(int friesQty, int burgerQty, int spaghettiQty, int drinksQty, int chickenQty)
        {
            StringBuilder orderQuantities = new StringBuilder("Orders: \n");
            if (friesQty > 0) orderQuantities.Append($"Fries: {friesQty.ToString()}\n");

            if (burgerQty > 0) orderQuantities.Append($"Burger: {burgerQty.ToString()}\n");
            
            if (spaghettiQty > 0) orderQuantities.Append($"Spaghetti: {spaghettiQty.ToString()}\n");

            if (drinksQty > 0) orderQuantities.Append($"Drinks: {drinksQty.ToString()}\n");

            if (chickenQty > 0) orderQuantities.Append($"Chicken: {chickenQty.ToString()}");

            lblOrderList.Text = orderQuantities.ToString();
        }

        public decimal discountCalc(decimal subtotal)
        {
            decimal discount = 0;
            if (chkSenior.Checked)
            {
                discount += subtotal * 0.20M;
            }

            else if (chkManager.Checked)
            {
                discount += subtotal * 0.15M;
            }

            else if (chkMember.Checked)
            {
                discount += subtotal * 0.10M;
            }

            return discount;
        }


  

        private void btnTryAgain_Click(object sender, EventArgs e)
        {
            txtFries.Text = String.Empty;
            txtBurger.Text = String.Empty;
            txtChicken.Text = String.Empty;
            txtDrinks.Text = String.Empty;
            txtSpaghetti.Text = String.Empty;
            lblOrderList.Text = "Orders:";
           
            chkSenior.Checked = false;
            chkManager.Checked = false;
            chkMember.Checked = false;
        }
    }
}
