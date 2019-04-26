require('cypress-xpath')

describe('My first cypress test', () => {
    it('Navigate to ea site', () => {
       cy.visit('https://quandora.eng.hintmd.com/sign-in')
    })

    it('Input your email', () => {
        cy.get('#email_login').type('dms@hintmd.com')
        cy.get('#submitEmailButton').click()
    })

    it('Input password', () =>  {
        cy.get('[data-test=login-password]').type('secret')
        cy.get('#submitPasswwordButton').click()
    })

    it('Go to one of provider', () => {
        cy.get('#TR0 > [style="color: rgb(46, 42, 50);"] > .kvVhJv > span').click()
    })

    it('Create new Patient', () => {
        //cy.get('#inputCustomersSearch').type('TestNewPatient1')
        cy.get('[style="width: 24px; height: 24px; border-radius: 50%; background: rgb(67, 183, 114); margin-right: 16px;"] > div > svg').click()
        cy.get(':nth-child(1) > .sc-cIShpX > div > img').click()
    })

    it('Handle Service Popup', () => {
        cy.get('[style="min-width: 118px; border-radius: 4px 0px 0px 4px; border-width: 1px;"]').click()
        cy.get('[style="margin-bottom: 40px;"] > .rangeslider').click()
        cy.get('#pbm-submit').click()
    })

    it('Continue for Checkout', () => {
        cy.get('#btnSubscriptionContinue').click()    
    })

    it('Fill in all details for customer and continue', () => {
        cy.get('#customerProfileSection-firstNameFieldOTC-em').type('Sagar-Cypress-TestFN1')
        cy.get('#customerProfileSection-lastNameFieldOTC-em').type('Sagar-Cypress-TestLN1')
        cy.get('#customerProfileSection-phoneFieldOTC-em').type('4085556675')
        cy.get('.Select-arrow-zone > span > svg').click()
        cy.get('#react-select-2--list > .is-focused').click()
        cy.get('.ripple').click()
    })

    it('Edit plan continue', () => {
        cy.get('.continueBtn').click()
        cy.get('#cd-ep-updateBtn').click()
        cy.get('[style="line-height: 56px; padding: 16px 0px; display: inline-block;"] > .chkContainer > .checkmark').click()
        cy.get('#checkout-nextBtn-em').click()
    })

    it('Actual Payment in Cash & Done', () => {
        cy.get('#payment-cashAmountField').type(' 3025.06')
        cy.get('#payment-nextBtn').click()
        cy.get('#paymentReceived-exitBtn-em').click()
        cy.get('#checkoutButton').click()
    })

    it('Today Treatment', () => {
        cy.get('[style="line-height: 56px; padding: 16px 0px; display: inline-block;"] > .chkContainer > .checkmark').click()
        cy.get('#checkout-nextBtn-em').click()
    })
})