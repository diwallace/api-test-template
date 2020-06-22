package dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListOfUsers {
    @SerializedName("total_count")
    private int totalCount;
    @SerializedName("incomplete_results")
    private boolean incompleteResults;
    private List<User> items;

    public int getTotalCount() {
        return totalCount;
    }

    public ListOfUsers setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public ListOfUsers setIncompleteResults(boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
        return this;
    }

    public List<User> getItems() {
        return items;
    }

    public ListOfUsers setItems(List<User> items) {
        this.items = items;
        return this;
    }
}
