package jb.service;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JSqlParserTest {

    @Test
    public void shouldParseSimpleCount() throws JSQLParserException {
        Statement stmt = CCJSqlParserUtil.parse("select count(*) from customer;");
        assertTrue(stmt instanceof Select);
        SelectBody sb = ((Select) stmt).getSelectBody();
        assertTrue(sb instanceof PlainSelect);
        assertEquals(
                "customer",
                ((PlainSelect) sb).getFromItem().toString()
        );
    }

    @Test
    public void shouldParsePostgresSpecific() throws JSQLParserException {
        /*
        Statement stmt = CCJSqlParserUtil.parse(
                """
                            select a.amname, p.name, pg_indexam_has_property(a.oid,p.name)
                            from pg_am a,
                                 unnest(array['can_order','can_unique','can_multi_col','can_exclude']) p(name)
                            where a.amname = 'btree'
                            order by a.amname;
                        """
        );
        */
    }

}
