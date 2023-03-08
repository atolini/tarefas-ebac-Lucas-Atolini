import java.util.Calendar;

public class Client {
    private Integer age;
    private Boolean activeRegistration;
    private String firstName;
    private String lastName;

    public Client(Integer age, Boolean activeRegistration, String firstName, String lastName) {
        this.age = age;
        this.activeRegistration = activeRegistration;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public Boolean getActiveRegistration() {
        return activeRegistration;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setActiveRegistration(Boolean activeRegistration) {
        this.activeRegistration = activeRegistration;
    }

    /**
     * Calcula o ano de nascimento baseado na idade.
     * @return Ano de nascimento.
     */
    public Integer calculateBirthYear() {
        Calendar calendar = Calendar.getInstance();
        final Integer YEAR = calendar.get(Calendar.YEAR);
        return YEAR - this.age;
    }
}
