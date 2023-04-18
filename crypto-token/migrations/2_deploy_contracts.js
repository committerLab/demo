const ZakatToken = artifacts.require("./zakat.sol");

const web3 = require("web3-utils");

module.exports = (deployer, network, [owner]) => {
  return deployer
    .then(() => deployer.deploy(ZakatToken))
    .then(() => ZakatToken.deployed())
};