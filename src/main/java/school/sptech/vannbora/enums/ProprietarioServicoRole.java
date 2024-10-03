package school.sptech.vannbora.enums;

public enum ProprietarioServicoRole {
    ADMIN("admin"),
    USER("user");

    private String role;

    ProprietarioServicoRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
