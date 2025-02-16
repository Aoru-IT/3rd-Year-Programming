using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace AlonzoDivino_EA2.Migrations
{
    /// <inheritdoc />
    public partial class AddLastSyncedQuantityToCustomer : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<int>(
                name: "LastSyncedQuantity",
                table: "Customers",
                type: "int",
                nullable: false,
                defaultValue: 0);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "LastSyncedQuantity",
                table: "Customers");
        }
    }
}
