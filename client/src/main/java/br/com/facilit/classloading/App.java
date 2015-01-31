package br.com.facilit.classloading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import br.com.facilit.classloading.service.Animal;

/**
 * Hello world!
 *
 */
public class App {

	private static final Logger log = Logger.getLogger(App.class);

	static String[] urls = new String[] {
			"file:/D:/dev/java/classloading/implementations/impl-1/client/src/main/resources/impl-1-1.0-SNAPSHOT.jar",
			"file:/D:/dev/java/classloading/implementations/impl-1/client/src/main/resources/impl-1-1.1-SNAPSHOT.jar",
			"file:/D:/dev/java/classloading/implementations/impl-2/client/src/main/resources/impl-2-1.0-SNAPSHOT.jar", };

	static Animal animal = null;
	static Animal oldAnimal = null;

	public static void main(String[] args) {

		BasicConfigurator.configure();

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		String in = "";

		while (true) {

			System.out.print("\nDigite quit para sair ou alguma posicao do array:");

			try {

				in = bufferedReader.readLine();

			} catch (IOException e) {
				log.error(e);
				continue;
			}

			// in = console.readLine();

			if (in.equalsIgnoreCase("quit") || in.equalsIgnoreCase("q") || in.equalsIgnoreCase("exit")) {
				break;
			}

			int i = 0;

			try {

				i = Integer.parseInt(in);

			} catch (NumberFormatException e) {
				log.error(e);
				continue;
			}

			URLClassLoader classLoader = null;

			try {

				classLoader = new URLClassLoader(new URL[] { new URL(urls[i]) });

			} catch (Exception e) {
				log.error(e);
				continue;
			}

			try {

				Class<?> animalImpl = classLoader.loadClass("br.com.facilit.classloading.service.impl.Dog");

				Animal curAnimal = Animal.class.cast(animalImpl.newInstance());

				curAnimal.setId(1);

				animal = curAnimal;

			} catch (Exception e) {

				log.error(e);
				continue;
			}

			if (animal != null) {
				System.out.println("(animal) Breed: " + animal.getBreed() + " name: " + animal.getName());
			}

			if (oldAnimal != null && animal != null) {

				if (oldAnimal.equals(animal)) {

					System.out.println("they are the same animal");

				} else {

					System.out.println("they are not the same animal");
				}
			}

			oldAnimal = animal;

			try {

				// releases the jar file. try deleting the jar without it (you wont get it done)
				
				classLoader.close();

			} catch (IOException e) {
				log.warn(e);
			}
		}
	}
}
