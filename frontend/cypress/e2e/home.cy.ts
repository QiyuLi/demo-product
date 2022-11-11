// https://docs.cypress.io/api/introduction/api.html

describe("Search Test", () => {
  it("visits the root url", () => {
    cy.visit("/");
    cy.contains("p", "No Data");
  });

  it("searches a given product", () => {
    cy.visit("/");
    cy.get(".ant-input").click();
    cy.get(".ant-input").type("some product");
    cy.get(".ant-input").type("{enter}");
    cy.get(".ant-card").should("exist");
    cy.get(".ant-card-cover img").first().click();
    cy.get("div:nth-child(4) img").click();
    cy.contains("Product Detail");
    cy.contains("Customers also considered");
  });
});
