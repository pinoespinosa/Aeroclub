package base_datos;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import data.Vuelo;
import extended.MainController;

public class Email {

	public static void send(List<Vuelo> vuelos) {

		double fechaUltimo = 0;

		try {

			String valor = managerDB.executeScript_Query("SELECT * FROM " + MainController.getEsquema() + ".licencia WHERE valor='emailDate';", "dato").get(0);
			fechaUltimo = Double.parseDouble(valor);

		} catch (Exception e) {
			System.out.println("falle pero creoo");
			managerDB.executeScript_Void("INSERT INTO `" + MainController.getEsquema() + "`.`licencia` (`valor`, `dato`) VALUES ('emailDate', '0');");
		}

		Date fecha = new Date();
		Calendar aa = new GregorianCalendar();
		int diaSemana = aa.get(Calendar.DAY_OF_WEEK);

		if (diaSemana == Calendar.TUESDAY && System.currentTimeMillis() - fechaUltimo > 3600 * 24 * 2) {
			
			managerDB.executeScript_Void("UPDATE " + MainController.getEsquema() + ".licencia SET dato='"+System.currentTimeMillis()+"' WHERE valor='emailDate';");

			final String username = "techsystemtandil@gmail.com";
			final String password = "36442114";

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("aeroclubTandil@gmail.com"));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("pino.espinosa91@gmail.com, aeroclubtandil@gmail.com, oroselli48@gmail.com "));
				message.setSubject("Reporte de Vuelos");

				String texto = "";

				for (Vuelo vuelo : vuelos) {
					if (System.currentTimeMillis() - vuelo.getHoraFinal() < 1000 * 60 * 60 * 24 * 7)
						texto += new Date(vuelo.getHoraInicio()).toLocaleString() + " " + vuelo.getPiloto().getApellido() + "," + vuelo.getPiloto().getName() + " $" + vuelo.getPrecio() + "\n";

				}

				message.setText(texto);

				Transport.send(message);

				System.out.println("Report saved.");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}
	}

}