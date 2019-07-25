import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Formatter;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) throws Exception{
		getSortedPersonListByBirthDay("File.txt", "Persons.txt");
	}
	public static void getSortedPersonListByBirthDay(String fileName1,String fileName2) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(fileName1));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName2)));
		List<Person> list = br
				.lines()
				.map(s -> s.split(","))
				.map(s -> new Person(s[0], LocalDate.parse(s[1],DateTimeFormatter.ofPattern("dd-MM-yyyy"))))
				.sorted(Comparator.comparing(Person::getBirthday))
				.collect(Collectors.toList());
		br.close();
		list.stream().map(s -> getPersonToString(s)).forEach(s -> pw.println(s));
		pw.flush();
		pw.close();
		
	}
	private static String getPersonToString(Person person) {
		return person.getName()+","+person.getBirthday();
	}
}
