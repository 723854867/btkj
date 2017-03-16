package org.btkj.web.util.session;

public interface ISession {
	
	void write(String reply);

	void write(byte[] reply);
}
