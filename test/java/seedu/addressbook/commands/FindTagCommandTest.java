package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TypicalPersons;

public class FindTagCommandTest {

    private final AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();
    private final TypicalPersons td = new TypicalPersons();

    @Test
    public void execute() {
        //same word, same case: matched
        assertFindTagCommandBehavior(new String[]{"Friend"}, Arrays.asList(td.candy, td.dan));

        //same word, different case: unmatched
        assertFindTagCommandBehavior(new String[]{"friend"}, Collections.emptyList());

        //partial word: not matched
        assertFindTagCommandBehavior(new String[]{"Fam"}, Collections.emptyList());

        //multiple words: matched
        assertFindTagCommandBehavior(new String[]{"Friend", "Family"},
                Arrays.asList(td.candy));

        //repeated keywords: matched
        assertFindTagCommandBehavior(new String[]{"Friend", "Friend"}, Arrays.asList(td.candy, td.dan));

        //Keyword matching a word in address: not matched
        assertFindTagCommandBehavior(new String[]{"Clementi"}, Collections.emptyList());
    }

    /**
     * Executes the find command for the given keywords and verifies
     * the result matches the persons in the expectedPersonList exactly.
     */
    private void assertFindTagCommandBehavior(String[] keywords, List<ReadOnlyPerson> expectedPersonList) {
        FindTagCommand command = createFindTagCommand(keywords);
        CommandResult result = command.execute();

        assertEquals(Command.getMessageForPersonListShownSummary(expectedPersonList), result.feedbackToUser);
    }

    private FindTagCommand createFindTagCommand(String[] keywords) {
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        FindTagCommand command = new FindTagCommand(keywordSet);
        command.setData(addressBook, Collections.emptyList());
        return command;
    }

}