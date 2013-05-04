package nz.govt.studylink.mslapp.servlet;

import java.util.logging.Logger;

import org.apache.camel.ShutdownRunningTask;
import org.apache.camel.spi.ShutdownAware;

public class ShutdownAwareImpl implements ShutdownAware {

	private static final Logger log = Logger.getLogger(ShutdownAwareImpl.class.getName());
	
	@Override
	public void prepareShutdown(boolean forced) {
		// TODO Auto-generated method stub
		log.info("prepareShutdown forced = " + forced);
	}

	@Override
	public boolean deferShutdown(ShutdownRunningTask shutdownRunningTask) {
		// TODO Auto-generated method stub
		log.info("deferShutdown return false");
		return false;
	}

	@Override
	public int getPendingExchangesSize() {
		// TODO Auto-generated method stub
		log.info("getPendingExchangesSize return 0");
		return 0;
	}

}
