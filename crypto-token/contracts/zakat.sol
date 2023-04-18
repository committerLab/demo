// contracts/ExampleToken.sol
// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;
import "@openzeppelin/contracts/token/ERC20/ERC20.sol";
contract zakat is ERC20 {
  constructor () ERC20("zakat", "ZKT") {
    _mint(msg.sender, 10000 * 10 ** decimals());
  }
}