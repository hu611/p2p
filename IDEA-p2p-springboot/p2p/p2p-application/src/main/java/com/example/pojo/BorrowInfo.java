package com.example.pojo;


public class BorrowInfo {
    public int loan_id;
    public int borrower_id;

    public BorrowInfo(int loan_id, int borrower_id) {
        this.loan_id = loan_id;
        this.borrower_id = borrower_id;
    }

    public int getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(int loan_id) {
        this.loan_id = loan_id;
    }

    public int getBorrower_id() {
        return borrower_id;
    }

    public void setBorrower_id(int borrower_id) {
        this.borrower_id = borrower_id;
    }
}
