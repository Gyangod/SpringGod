package com.gyangod.config;

import com.gyangod.enums.statemachine.PackageEvents;
import com.gyangod.enums.statemachine.PackageState;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import static com.gyangod.constants.StateMachineConstant.PACKAGE_STATE_MACHINE_HEADER;
import static com.gyangod.enums.statemachine.PackageEvents.*;
import static com.gyangod.enums.statemachine.PackageState.*;

@Configuration
@EnableStateMachineFactory(name = "package")
public class PackageStateMachine extends StateMachineConfigurerAdapter<PackageState, PackageEvents> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<PackageState, PackageEvents> config) throws Exception {
        config.withConfiguration().autoStartup(false).listener(new StateMachineListenerAdapter<>() {
            @Override
            public void stateChanged(State<PackageState, PackageEvents> from, State<PackageState, PackageEvents> to) {
                //TODO: Log it while the state is changed.
                super.stateChanged(from, to);
            }
        });
    }

    @Override
    public void configure(StateMachineStateConfigurer<PackageState, PackageEvents> states) throws Exception {
        states.withStates().initial(ACTIVE).stateEntry(ACTIVE, stateContext -> {
            stateContext.getExtendedState().getVariables().getOrDefault(PACKAGE_STATE_MACHINE_HEADER, "default");
            //TODO: Log this activity
        }).state(STUDENT).state(INVISIBLE).end(INACTIVE);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<PackageState, PackageEvents> transitions) throws Exception {
        transitions
                //making a package active
                .withExternal().source(STUDENT).target(ACTIVE).event(activate).and()
                .withExternal().source(INACTIVE).target(ACTIVE).event(activate).and()
                //toggling a package visibility
                .withExternal().source(ACTIVE).target(INVISIBLE).event(change).and()
                .withExternal().source(INVISIBLE).target(ACTIVE).event(change).and()
                //making a package inactive
                .withExternal().source(ACTIVE).target(INACTIVE).event(deactivate).and()
                .withExternal().source(INVISIBLE).target(INACTIVE).event(deactivate);
    }
}
