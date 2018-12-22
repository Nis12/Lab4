import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.StringReader;
import java.io.StringWriter;

class SerialXML {

    void deSerial(String str) {
        try {
            StringReader reader = new StringReader(str);
            JAXBContext context = JAXBContext.newInstance(Person.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Person person = (Person) unmarshaller.unmarshal(reader);
            System.out.println(person.toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    String serial(String firstName, String lastName, String group, String progLang) {
        Person person = new Person();
        person.firstName = firstName;
        person.lastName = lastName;
        person.group = group;
        person.progLang = progLang;

        try {
            StringWriter writer = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(Person.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(person, writer);
            return writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    @XmlType(name = "person")
    @XmlRootElement(name = "person")
    public static class Person {
        String firstName;
        String lastName;
        String group;
        String progLang;

        Person() { }

        public String getFirstName() {
            return firstName;
        }
        public void setFirstName(String name) { //сеттер
            this.firstName=name;
        }
        public String getLastName() {
            return lastName;
        }
        public void setLastName(String name) { //сеттер
            this.lastName=name;
        }
        public String getGroup() {
            return group;
        }
        public void setGroup(String name) { //сеттер
            this.group=name;
        }
        public String getProgLang() {
            return progLang;
        }
        public void setProgLang(String name) { //сеттер
            this.progLang=name;
        }
        public String toString() {
            return "Содержимое Person: " + firstName + ", " + lastName + ", " + progLang + ", " + group;
        }
    }
}
