# CLI Convert JSON to Smile

The author does not regularly use Java or Maven, so any improvements
are welcome. Build this with:

    mvn clean compile assembly:single

Then try it out with:

    echo '24162543621642542354612534126.5415324146' | java -jar ./target/smilenumber-1.0-jar-with-dependencies.jar | xxd

The application expects a single number on standard in and outputs
SMILE-encoded JSON (just one number) as SMILE's `BigDecimal` token.
In the example above, the output is piped through `xxd` to avoid
spewing garbage on the terminal.
