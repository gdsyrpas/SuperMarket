package Entities;
public class User {
    private String username;
    private String role;

    // Κατασκευαστής για τον χρήστη
    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }

    // Getter για το όνομα χρήστη
    public String getUsername() {
        return username;
    }

    // Setter για το όνομα χρήστη
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter για τον ρόλο
    public String getRole() {
        return role;
    }

    // Setter για τον ρόλο
    public void setRole(String role) {
        this.role = role;
    }

    // Επανακαθορισμός της μεθόδου toString για καλύτερη εμφάνιση του χρήστη
    @Override
    public String toString() {
        return "Username: " + username + ", Role: " + role;
    }
}
