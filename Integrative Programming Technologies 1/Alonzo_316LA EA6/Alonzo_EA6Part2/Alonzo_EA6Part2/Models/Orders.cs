using System.Reflection.Metadata;
using System.Text;

namespace Alonzo_EA6Part2.Models
{
    public class Orders
    {
        public int friesQty { get; set; }
        public int burgerQty { get; set; }
        public int spaghettiQty { get; set; }
        public int drinksQty { get; set; }
        public int chickenQty { get; set; }

        public decimal Subtotal { get; set; }
        public decimal Total { get; set; }
        public string SelectedDiscount { get; set; }
        public string? displayOrderQuantity { get; set; }
        public decimal DiscountAmount { get; set; }

        decimal friesPrice = 72.00M;
        decimal burgerPrice = 58.00M;
        decimal spaghettiPrice = 65.00M;
        decimal drinksPrice = 39.00M;
        decimal chickenPrice = 98.00M; 

        public decimal DiscountComputation()
        {
            decimal discount = 0;
            if(SelectedDiscount == "Manager 25)")
            {
                discount = 0.25M;
            }
            else if(SelectedDiscount == "Senior/PWD 20%")
            {
                discount = 0.20M;
            }
            else if(SelectedDiscount == "Member 10%")
            {
                discount = 0.10M;
            }
            else if (SelectedDiscount == "None")
            {
                discount = 0;
            }

            return DiscountAmount = discount * Subtotal;
        }


        public decimal TotalComputation()
        {
            Total = Subtotal - DiscountAmount;

            displayQuantities(friesQty, burgerQty, spaghettiQty, drinksQty, chickenQty);
            return Total;
        }

        public void displayQuantities(int friesQty, int burgerQty, int spaghettiQty, int drinksQty, int chickenQty)
        {
            StringBuilder orderQuantities = new StringBuilder("<ul>");
            if (friesQty > 0) orderQuantities.Append($"<li>Fries: {friesQty.ToString()}</li>");

            if (burgerQty > 0) orderQuantities.Append($"<li>Burger: {burgerQty.ToString()}</li>");

            if (spaghettiQty > 0) orderQuantities.Append($"<li>Spaghetti: {spaghettiQty.ToString()}</li>");

            if (drinksQty > 0) orderQuantities.Append($"<li>Drinks: {drinksQty.ToString()}</li>");

            if (chickenQty > 0) orderQuantities.Append($"<li>Chicken: {chickenQty.ToString()}</li></ul>");

            displayOrderQuantity = orderQuantities.ToString();
        }


        public decimal SubtotalComputation(){
            Subtotal = qtyPrice(friesQty, friesPrice) + qtyPrice(burgerQty, burgerPrice) +
              qtyPrice(spaghettiQty, spaghettiPrice) + qtyPrice(drinksQty, drinksPrice) +
              qtyPrice(chickenQty, chickenPrice);

            return Subtotal;
        }


        public decimal qtyPrice(decimal qty, decimal price)
        {
            return qty * price;
        }



    }
}

