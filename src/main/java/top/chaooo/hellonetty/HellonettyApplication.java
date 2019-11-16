package top.chaooo.hellonetty;

import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import top.chaooo.hellonetty.server.NettyServer;

import java.net.InetSocketAddress;

@SpringBootApplication
@ComponentScan("top.chaooo.hellonetty")
public class HellonettyApplication implements CommandLineRunner {

	@Value("${netty.host}")
	private String host;
	@Value("${netty.port}")
	private int port;
	@Autowired
	private NettyServer nettyServer;

	public HellonettyApplication() {
	}

	public static void main(String[] args) {
		SpringApplication.run(HellonettyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		InetSocketAddress address = new InetSocketAddress(host, port);
		ChannelFuture channelFuture = nettyServer.bing(address);
		Runtime.getRuntime().addShutdownHook(new Thread(() -> nettyServer.destroy()));
		channelFuture.channel().closeFuture().syncUninterruptibly();
	}
}
