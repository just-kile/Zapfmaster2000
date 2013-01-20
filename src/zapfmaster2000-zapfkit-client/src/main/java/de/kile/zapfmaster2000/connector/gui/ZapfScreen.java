package de.kile.zapfmaster2000.connector.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import org.apache.commons.httpclient.HttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.DefaultedHttpContext;

import de.kile.zapfmaster2000.connector.Connector;
import de.kile.zapfmaster2000.connector.ConnectorListener;
import de.kile.zapfmaster2000.connector.web.WebCommunicator;

public class ZapfScreen extends JFrame {

	private JComponent contentPane;

	private JLabel label;

	private DefaultHttpClient client = new DefaultHttpClient();

	private ImagePanel image;

	private BufferedImage cardImage;

	private final static String TEXT_DEFAULT = "<html><p color=\"#FF71AB\" align=center><font size=6 align=center>Logge dich mit deiner Zapfkarte ein und beginne den Zapfvorgang.</font><p></html>";

	private final static String TEXT_ERROR = "<html><p color=\"#FF71AB\" align=center><font size=6 align=center>Unbekannte Karte!</font><p></html>";

	private final static String TEXT_USER = "<html><p color=\"#FF71AB\" align=center><font size=6 align=center>Hallo #USER#! Du darfst jetzt zapfen.</font><p></html>";

	private final static String TEXT_DRAW = "<html><p color=\"#FF71AB\" align=center><font size=6 align=center>#USER# hat jetzt #AMOUNT#l gezapft.</font><p></html>";

	
	private String curImage = null;
	
	private String curUser = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZapfScreen frame = new ZapfScreen(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public ZapfScreen(Connector connector) throws IOException {

		InputStream stream = getClass().getClassLoader().getResourceAsStream(
				"backgrnd2.jpg");
		BufferedImage myImage = ImageIO.read(stream);

		contentPane = new ImagePanel(myImage);
		setContentPane(contentPane);

		// contentPane.setLayout(new GridLayout(1, 1));

		stream = getClass().getClassLoader().getResourceAsStream(
				"zapfmaster.png");
		myImage = ImageIO.read(stream);
		ImagePanel logo = new ImagePanel(myImage);
		logo.setBounds(125, 10, 389, 160);
		contentPane.add(logo);

		label = new JLabel();
		label.setText(TEXT_DEFAULT);
		label.setBounds(300, 230, 305, 100);
		contentPane.add(label);

		stream = getClass().getClassLoader().getResourceAsStream("card.png");
		cardImage = ImageIO.read(stream);
		image = new ImagePanel(cardImage);
		image.setBounds(70, 200, 160, 160);
		contentPane.add(image);

		stream = getClass().getClassLoader()
				.getResourceAsStream("justKile.png");
		myImage = ImageIO.read(stream);
		ImagePanel justKileLogo = new ImagePanel(myImage);
		justKileLogo.setBounds(520, 410, 237, 80);
		contentPane.add(justKileLogo);

		JLabel product = new JLabel();
		product.setText("<html><font size = 4 color = #FFFFFF>Ein Produkt von</font></html>");
		product.setBounds(400, 415, 150, 20);
		contentPane.add(product);

		setSize(640, 480);
		setResizable(false);
		setUndecorated(true);
		setAlwaysOnTop(true);
		setLocation(0, 0);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if (connector != null) {
			connector.addListener(new ConnectorListener() {

				@Override
				public void onLogout() {
					curImage = null;
					label.setText(TEXT_DEFAULT);
					image.setImage(cardImage);
				}

				@Override
				public void onLoginFailure() {
					curImage = null;
					label.setText(TEXT_ERROR);
					image.setImage(cardImage);
				}

				@Override
				public void onLogin(String pUserName, String pImagePath) {
					if (curImage == null || !curImage.equals(pImagePath)) {
						label.setText(TEXT_USER.replace("#USER#", pUserName));

						HttpGet get = new HttpGet(WebCommunicator.BASE_URL
								+ pImagePath + "/big");
						try {
							HttpResponse resp = client.execute(get);
							BufferedImage i = ImageIO.read(resp.getEntity()
									.getContent());
							resp.getEntity().consumeContent();
							image.setImage(i);
							curImage = pImagePath;
							curUser = pUserName;
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				@Override
				public void onDraw(double amount) {
					label.setText(TEXT_DRAW.replace("#USER#", curUser).replace("#AMOUNT#", new DecimalFormat("#0.00").format(amount)));
				}
			});
			
			
		}
	}

	class ImagePanel extends JComponent {
		private Image image;

		public ImagePanel(Image image) {
			this.image = image;
		}

		@Override
		protected void paintComponent(Graphics g) {
			g.drawImage(image, 0, 0, null);
		}

		public void setImage(Image pImage) {
			image = pImage;
			repaint();
		}
	}

}
