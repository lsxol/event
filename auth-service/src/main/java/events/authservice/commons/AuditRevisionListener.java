package events.authservice.commons;

import events.authservice.config.security.AuthHelper;
import org.hibernate.envers.RevisionListener;

class AuditRevisionListener implements RevisionListener {

  @Override
  public void newRevision(Object o) {
    AuditableRevisionEntity revisionEntity = (AuditableRevisionEntity) o;
    revisionEntity.setUzytkownikId(AuthHelper.getAuth().getUserId());
  }

}
