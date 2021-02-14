package com.oneworld.claimit;

import android.widget.TextView;

public class CurrentBalance {
    private int currentBalanace;
    private TextView balanceTextView;

    public CurrentBalance(TextView balanceTextView) {
        this.currentBalanace = 0;
        this.balanceTextView = balanceTextView;
        setBalance();
    }

    public void increaseBalance(int increase) {
        currentBalanace += increase;
        setBalance();
    }

    public void decreaseBalance(int decrease) {
        currentBalanace -= decrease;
        setBalance();
    }

    public int getBalance() {
        return currentBalanace;
    }

    private void setBalance()  {
        balanceTextView.setText("$" + currentBalanace);
    }
}
