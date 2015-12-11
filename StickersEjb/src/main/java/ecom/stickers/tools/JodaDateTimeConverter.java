package ecom.stickers.tools;

import java.sql.Timestamp;

import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;
import org.joda.time.DateTime;

public class JodaDateTimeConverter implements Converter {

    private static final long serialVersionUID = 1L;

    public Object convertDataValueToObjectValue( Object dataValue, Session session ) {
        return null;//dataValue == null ? null : new DateTime( (Timestamp) dataValue );
    }

    public Object convertObjectValueToDataValue( Object objectValue, Session session ) {
        return objectValue == null ? null : new Timestamp( ( (DateTime) objectValue ).getMillis() );
    }

    public void initialize( DatabaseMapping mapping, Session session ) {
    }

    public boolean isMutable() {
        return false;
    }

}