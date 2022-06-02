package databasetest2;

public class TestDataHelperClass {
    public TestDataHelperClass(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    private String customerEmail;

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public String toString() {
        return "TestDataHelperClass{" +
                "customerEmail='" + customerEmail + '\'' +
                '}';
    }
}
