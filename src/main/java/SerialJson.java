import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;

class SerialJson {

    @JsonCreator
    void deSerial(String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Person person = mapper.readValue(jsonString, Person.class);
        System.out.println(person.toString());
    }

    String serial(String firstName, String lastName, String group, String progLang) throws IOException {
        Person person = new Person(firstName, lastName, group, progLang);

        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(person);
    }

    @JsonAutoDetect
    public static  class Person implements Serializable {
        private String firstName;
        private String  lastName;
        private String  group;
        private String  progLang;

        public Person() {

        }

        Person(String firstName, String lastName, String group, String progLang) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.group = group;
            this.progLang = progLang;
        }

        public String getFirstName() {
            return firstName;
        }
        public void setFirstName(String firstName) { //сеттер
            this.firstName=firstName;
        }
        public String getLastName() {
            return lastName;
        }
        public void setLastName(String lastName) { //сеттер
            this.lastName=lastName;
        }
        public String getGroup() {
            return group;
        }
        public void setGroup(String group) { //сеттер
            this.group=group;
        }
        public String getProgLang() {
            return progLang;
        }
        public void setProgLang(String progLang) { //сеттер
            this.progLang=progLang;
        }
        public String toString() {
            return "Содержимое Person: " + firstName + ", " + lastName + ", " + progLang + ", " + group;
        }
    }
}
