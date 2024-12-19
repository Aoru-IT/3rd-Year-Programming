using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Alonzo_NewbookRecords.Migrations
{
    /// <inheritdoc />
    public partial class InitialCreate : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Records",
                columns: table => new
                {
                    Id = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    FirstName = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    LastName = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    Email = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    PhoneNumber = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    College = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    PrelimCheck = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    MidtermCheck = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    FinalCheck = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    ScholarCheck = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    PendingFee = table.Column<decimal>(type: "decimal(18,2)", nullable: false),
                    TotalFee = table.Column<decimal>(type: "decimal(18,2)", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Records", x => x.Id);
                });
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Records");
        }
    }
}
