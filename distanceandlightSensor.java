import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.util.Enumeration;

public class distanceandlightSensor implements SerialPortEventListener {

	//Arduino interfacing code from http://arduino.cc/playground/Interfacing/Java
	
	SerialPort serialPort;
	/** The port we're normally going to use. */
	private static final String PortName = "COM10";
	/** Buffered input stream from the port */
	private InputStream input;
	/** The output stream to the port */
	private OutputStream output;
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;

	JFrame frame = new JFrame("Welcome Screen");
	JPanel panel = new JPanel(); // the panel is not visible in output
	JTextArea ta = new JTextArea();

	public void initialize() {
		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		// iterate through, looking for the port
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum
					.nextElement();
			String portName = PortName;
			if (currPortId.getName().equals(portName)) {
				portId = currPortId;
				break;
			}

		}

		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

			// open the streams
			input = serialPort.getInputStream();
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		panel.add(ta);
		frame.getContentPane().add(BorderLayout.SOUTH, panel);
		frame.getContentPane().add(BorderLayout.CENTER, ta);
		frame.setVisible(true);
	}

	public static void main(String args[]) {

		distanceandlightSensor main = new distanceandlightSensor();
		main.initialize();
		System.out.println("Started");
	}

	/**
	 * This should be called when you stop using the port. This will prevent
	 * port locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	@Override
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				int available = input.available();
				byte chunk[] = new byte[available];
				input.read(chunk, 0, available);

				// Displayed results are codepage dependent
				String sensorValue = new String(chunk);
				System.out.println(sensorValue.trim());
				int sensorVal = 0;
				try {
					if (sensorValue != " ") {
						sensorVal = Integer.parseInt(sensorValue);
					}
				} catch (Exception e) {
					// e.printStackTrace();
				}
				if (sensorVal > 100) {
					ta.setFont(new Font("Serif", Font.ITALIC, sensorVal));
					// ta.setForeground(Color.blue);
					ta.setBackground(Color.BLACK);
					ta.setText("Hello");
					if (sensorVal > 200) {
						ta.setForeground(new Color(0xff, 0xff, 0xff));
					} else if (sensorVal > 150) {
						ta.setForeground(new Color(0x00, 0xff, 0xff));
					} else if (sensorVal > 100) {
						ta.setForeground(new Color(0x00, 0x00, 0xff));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		// Ignore all the other eventTypes, but you should consider the other
		// ones.
	}
}
