package jp.ac.titech.itpro.sdl.accountbook;

public class User {
  private String email;
  private String cookie;

  public User(String email, String cookie) {
    this.email = email;
    this.cookie = cookie;
  }

  public String getCookie() {
    return cookie;
  }

  public String getEmail() {
    return email;
  }
}
