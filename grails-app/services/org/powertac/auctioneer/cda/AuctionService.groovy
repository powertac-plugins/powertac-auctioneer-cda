package org.powertac.auctioneer.cda

import org.powertac.common.Broker
import org.powertac.common.Competition
import org.powertac.common.IdGenerator
import org.powertac.common.Shout
import org.powertac.common.command.ShoutDoCreateCmd
import org.powertac.common.command.ShoutDoDeleteCmd
import org.powertac.common.command.ShoutDoUpdateCmd
import org.powertac.common.exceptions.ShoutCreationException
import org.powertac.common.interfaces.Auctioneer

class AuctionService implements Auctioneer {

  static transactional = false

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

  Shout processShoutDelete(ShoutDoDeleteCmd shoutDoDeleteCmd) {
    return null  //To change body of implemented methods use File | Settings | File Templates.
  }

  List processShoutUpdate(ShoutDoUpdateCmd shoutDoUpdateCmd) {
    return null  //To change body of implemented methods use File | Settings | File Templates.
  }

  List clearMarket() {
    return null  //To change body of implemented methods use File | Settings | File Templates.
  }

  void competitionBeforeStart(Competition competition) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  void competitionAfterStart(Competition competition) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  void competitionBeforeStop(Competition competition) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  void competitionAfterStop(Competition competition) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  void competitionReset(Competition competition) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

}
