package viewmodel;

import androidx.lifecycle.ViewModel;

public class MyScrollPosition extends ViewModel {
    private int scrollPosition;
    public void setScrollPosition(int position) {
        this.scrollPosition = position;
    }
    public int getScrollPosition() {
        return scrollPosition;
    }
}
