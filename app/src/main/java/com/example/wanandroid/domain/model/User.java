//User的model
package com.example.wanandroid.domain.model;

public class User {
    private final int id;
    private final int coinCount;//用户积分
    private final String username;
    private final String token;//用户登录凭证
    private final String rank;//用户排名

    public User(int id, int coinCount, String username, String token, String rank) {
    this.id = id;
    this.coinCount = coinCount;
    this.username = username;
    this.token = token;
    this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public int getCoinCount() {
        return coinCount;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", coinCount=" + coinCount
                + ", username='" + username + '\''+"}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this){
            return true;
        }
        if (!(o instanceof User)){
            return false;
        }
        User other = (User) o;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
