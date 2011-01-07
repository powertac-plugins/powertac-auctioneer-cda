package org.powertac.auctioneer.cda

import org.powertac.common.Broker
import org.powertac.common.Shout
import org.powertac.common.command.ShoutDoCreateCmd
import org.powertac.common.command.ShoutDoDeleteCmd
import org.powertac.common.command.ShoutDoUpdateCmd
import org.powertac.common.command.ShoutIsChangedCmd
import org.powertac.common.interfaces.Auctioneer
import org.powertac.common.IdGenerator

class AuctionService implements Auctioneer {

  static transactional = false

  List processShoutCreate(ShoutDoCreateCmd shoutDoCreateCmd) {
    if (!shoutDoCreateCmd.validate()) return shoutDoCreateCmd.errors.allErrors

    def transactionId = IdGenerator.createId()
    def shoutId = IdGenerator.createId()
    def broker = Broker.findByUserName(shoutDoCreateCmd.userName)

    Shout shoutInstance = new Shout('competition.id': shoutDoCreateCmd.competitionId, 'product.id': shoutDoCreateCmd.productId, 'timeslot.id': shoutDoCreateCmd.timeslotId, broker: broker, quantity: shoutDoCreateCmd.quantity, buySellIndicator: shoutDoCreateCmd.buySellIndicator, orderType: shoutDoCreateCmd.orderType, transactionId: transactionId, latest: true, shoutId: shoutId)
    if (!shoutInstance.validate()) {
      def errors = shoutInstance.errors.allErrors
      shoutInstance.discard()
      log.error errors
      return errors
    }
    shoutInstance.save()
    return [new ShoutIsChangedCmd(id: shoutInstance.id, competitionId: shoutInstance.competition.id, brokerId: shoutInstance.broker.id, productId: shoutInstance.product.id, timeslotId: shoutInstance.timeslot.id, buySellIndicator: shoutInstance.buySellIndicator, quantity: shoutInstance.quantity, limitPrice: shoutInstance.limitPrice, executionQuantity: shoutInstance.executionQuantity, executionPrice: shoutInstance.executionPrice, orderType: shoutInstance.orderType, dateCreated: shoutInstance.dateCreated, dateMod: shoutInstance.dateMod, modReasonCode: shoutInstance.modReasonCode, transactionId: shoutInstance.transactionId, shoutId: shoutInstance.shoutId, comment: shoutInstance.comment, latest: shoutInstance.latest)]
  }

  ShoutIsChangedCmd processShoutDelete(ShoutDoDeleteCmd shoutDoDeleteCmd) {
    return null  //To change body of implemented methods use File | Settings | File Templates.
  }

  List processShoutUpdate(ShoutDoUpdateCmd shoutDoUpdateCmd) {
    return null  //To change body of implemented methods use File | Settings | File Templates.
  }

  List clearMarket() {
    log.info "CDA auctioneer clears on every incoming shout. Periodic clearing requests are ignored."
    return null
  }

  void competitionBeforeStart(String competitionId) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  void competitionAfterStart(String competitionId) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  void competitionBeforeStop(String competitionId) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  void competitionAfterStop(String competitionId) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  void competitionReset(String competitionId) {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}
