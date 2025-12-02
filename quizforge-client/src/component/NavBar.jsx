import React from 'react'

const NavBar = () => {
  const APP_NAME = import.meta.env.VITE_APP_NAME;

  return (
    <div className="flex justify-evenly b">
      <button>{APP_NAME}</button>
      <button>Sign up</button>
      <button>Log in</button>
      <button>About</button>
    </div>
  )
}

export default NavBar
