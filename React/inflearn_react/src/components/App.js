import React from 'react';
import Contact from './Contact'
//import ContactInfo from './ContactInfo'

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            value: 0
        }
    }

    render(){

        return (
            <div>
                <Contact></Contact>
            </div>
        );
    }
}

export default App;
