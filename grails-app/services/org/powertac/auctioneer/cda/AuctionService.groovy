package org.powertac.auctioneer.cda

import org.powertac.common.Broker
import org.powertac.common.IdGenerator
import org.powertac.common.Shout
import org.powertac.common.exceptions.ShoutCreationException
import org.powertac.common.interfaces.Auctioneer

class AuctionService implements Auctioneer {

  static transactional = false
/* TODO change code for new common plugin
  List processShoutCreate(ShoutDoCreateCmd shoutDoCreateCmd) throws ShoutCreationException {
    def broker = Broker.findByUserName(shoutDoCreateCmd.userName)
    Shout shoutInstance = new Shout(shoutDoCreateCmd.properties)
    shoutInstance.transactionId = IdGenerator.createId()
    shoutInstance.latest = true
    shoutInstance.shoutId = IdGenerator.createId()
    if (!shoutInstance.validate() || !shoutInstance.save()) {
      shoutInstance.discard()
      return shoutInstance.errors.allErrors
    }
    return [shoutInstance]
  }

  List processShoutDelete(ShoutDoDeleteCmd shoutDoDeleteCmd) {
    return null  //To change body of implemented methods use File | Settings | File Templates.
  }

  List processShoutUpdate(ShoutDoUpdateCmd shoutDoUpdateCmd) {
    return null  //To change body of implemented methods use File | Settings | File Templates.
  }*/  

  public void processShout(Shout shout){return};
  
  
  void clearMarket() {
    return null  //To change body of implemented methods use File | Settings | File Templates.
  }
}
