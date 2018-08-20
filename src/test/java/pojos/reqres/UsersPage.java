package pojos.reqres;

import pojos.reqres.User;

import java.util.List;

public class UsersPage {

    public int page;

    public int per_page;

    public int total;

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

    public List<User> data;

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }
    public int total_pages;

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "UsersPage{" +
                "page=" + page +
                ", per_page=" + per_page +
                ", total=" + total +
                ", total_pages=" + total_pages +
                ", data=" + data +
                '}';
    }
}
