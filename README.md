# Read Me

This project demonstrates how to call a MySql stored procedure

# Postgres

There is an example here that uses
https://snowcloudbyte.medium.com/spring-boot-3-call-stored-procedure-and-function-from-postgresql-b303534776ae

```
CREATE OR REPLACE PROCEDURE get_user_profiles(in user_id CHARACTER VARYING, out ref refcursor)
LANGUAGE plpgsql
AS $$
BEGIN
    -- Open a cursor and select all rows from user_profiles
    OPEN ref FOR 
 SELECT * FROM user_profiles
 where user_profiles.id::text = user_id;
END;
$$;
```

```
CallableStatement call = connection.prepareCall("call get_user_profiles(?, ?)");
call.setString(1, userId);
call.registerOutParameter(2, Types.OTHER);
call.execute();
ResultSet rs = (ResultSet) call.getObject(2);
while (rs.next()) {
   ...
}
```