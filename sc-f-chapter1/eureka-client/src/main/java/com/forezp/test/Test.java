package com.forezp.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Test {
	public void selector() throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		Selector selector = Selector.open();
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false);//设置为非阻塞发那个方式
		ssc.socket().bind(new InetSocketAddress(8080));
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		while(true) {
			Set selectedKeys = selector.selectedKeys();
			Iterator it = selectedKeys.iterator();
			while(it.hasNext()) {
				SelectionKey key = (SelectionKey) it.next();
				if((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
					ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
					java.nio.channels.SocketChannel sc = ssChannel.accept();
					sc.configureBlocking(false);
					sc.register(selector, SelectionKey.OP_READ);
					it.remove();
				} else if((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
					java.nio.channels.SocketChannel sc =  (java.nio.channels.SocketChannel) key.channel();
					while (true) {
						buffer.clear();
						int n = sc.read(buffer);
						if (n <= 0)
							break;
						buffer.flip();
					}
					it.remove();
				}
			}
		}
	}
}
