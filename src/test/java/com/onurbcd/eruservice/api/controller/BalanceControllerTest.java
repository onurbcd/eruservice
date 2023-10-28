package com.onurbcd.eruservice.api.controller;

import com.onurbcd.eruservice.TestConstants;
import com.onurbcd.eruservice.dto.enums.Direction;
import com.onurbcd.eruservice.service.BalanceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class BalanceControllerTest {

    @InjectMocks
    private BalanceController controller;

    @Mock
    private BalanceService balanceService;

    @Test
    void givenIdAndDirection_shouldCallUpdateSequence() {
        doNothing().when(balanceService).updateSequence(TestConstants.ID, Direction.UP);
        controller.updateSequence(TestConstants.ID, Direction.UP);
        verify(balanceService).updateSequence(TestConstants.ID, Direction.UP);
    }

    @Test
    void givenIdAndTargetSequence_shouldCallSwapPosition() {
        doNothing().when(balanceService).swapPosition(TestConstants.ID, TestConstants.TARGET_SEQUENCE);
        controller.swapPosition(TestConstants.ID, TestConstants.TARGET_SEQUENCE);
        verify(balanceService).swapPosition(TestConstants.ID, TestConstants.TARGET_SEQUENCE);
    }
}
