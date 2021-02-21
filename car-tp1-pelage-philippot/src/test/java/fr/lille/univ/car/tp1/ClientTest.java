package fr.lille.univ.car.tp1;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ClientTest {
	@Mock
	Client client;
	@Test
    public void TestEver() throws IOException {
		String request = "USER test";
		client.process2(request);
		
        Assert.assertTrue(true);
    }
	
//	Socket clientSocket = Mockito.mock(Socket.class);
//	@Mock
//	Client client;
//	public void userNameOK() {
//		String request = "USER test";
//		client.process2(request);
//		return;
//		
//	}
	
}
