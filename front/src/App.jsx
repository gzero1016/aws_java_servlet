import { Route, Routes } from "react-router-dom";
import MainLayout from "./components/MainLayout/MainLayout";
/** @jsxImportSource @emotion/react */
import { Global, css } from "@emotion/react";
import Signup from "./pages/Signup/Signup";

const SCommon = css`
  * {
    box-sizing: border-box;
  }
`;

function App() {
  return (
    <>
      <Global styles={SCommon} />
      <MainLayout>
        <Routes>
          <Route path="/" />
          <Route path="/signin" element={ <></> } />
          <Route path="/signup" element={ <Signup /> } />
        </Routes>
      </MainLayout>
    </>
  );
}

export default App;
